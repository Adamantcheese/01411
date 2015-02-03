
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToyScanner {

    private Trie trie;
    private boolean isMultLineComment;
    private PriorityQueue<Integer> out;

    public ToyScanner (int maxSymbols) {
        isMultLineComment = false;
        out = new PriorityQueue<Integer>();
        trie = new Trie(ToyLangUtil.getReserved(), maxSymbols);
    }

    //Scan any given file to the lexical analyzer
    public void scanFile (String fileName, boolean verbose) throws FileNotFoundException, EOFException {
        Scanner scFile = new Scanner(new File(fileName));
        while (scFile.hasNext()) {
            String tempLine = scFile.nextLine().trim();
            scanLine(tempLine, verbose);
        }
        if (isMultLineComment) {
            throw new EOFException();
        }
    }

    //The method that scans each line into words for the trie
    //Reserved flag for denoting if a line contains reserved words or not
    //Verbosity flag for debugging and output reasons
    private void scanLine (String line, boolean verbose) {
        //Empty lines are ignored
        if (line.isEmpty()) {
            return;
        }
        //Find all tokens in the line that fit the language accordingly
        Pattern p = Pattern.compile(ToyLangUtil.totRegex);
        Matcher m = p.matcher(line);
        ArrayList<String> tokens = new ArrayList<String>();
        while (m.find()) {
            tokens.add(line.substring(m.start(), m.end()));
        }
        String[] strTokens = new String[tokens.toArray().length];
        strTokens = tokens.toArray(strTokens);
        //Parse each token individually
        for (int i = 0; i < strTokens.length; i++) {
            //Skip whitespace
            if (ToyLangUtil.isWhitespace(strTokens[i])) {
                continue;
            }
            //Skip the rest of the line once a "//" token appears
            else if (strTokens[i].equals("//")) {
                break;
                //Mark if we are in a multi line comment or not as indicated by appearance of markers
            } else if (strTokens[i].equals("/*")) {
                //We can put anything inside a multiple line comment, including starting another one
                isMultLineComment = true;
            } else if (strTokens[i].equals("*/")) {
                //We cannot have nested comments though, so if we aren't in a multiple line comment and an ending marker
                //appears, we parse it as an invalid token
                if (isMultLineComment) {
                    isMultLineComment = false;
                }
            }
            //We need to not be in a multi line comment to parse the word
            else if (!isMultLineComment) {
                if(ToyLangUtil.isIdentifier(strTokens[i])) {
                    trie.scanWord(strTokens[i], false);
                }
                //Add the scanned token to a list of output, for use later
                out.add(new Integer(ToyLangUtil.stringToToken(strTokens[i])));
                if (verbose) {
                    System.out.print(ToyLangUtil.getStringToken(ToyLangUtil.stringToToken(strTokens[i])).substring(1) + ' ');
                }
            }
        }
        //If verbosity is enabled, print out a newline
        if (verbose) {
            System.out.print("\n");
        }
    }

    public int yylex () {
        if (out.peek() == null) {
            return -2;
        } else {
            return out.poll();
        }
    }

    public void showTables () {
        trie.prettyPrint(15);
    }
}
