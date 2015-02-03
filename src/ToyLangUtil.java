import java.util.regex.Pattern;

public class ToyLangUtil {
    //Possible tokens that the Toy scanner will produce
    private static final String[] tokens = {"_boolean", "_break", "_class", "_double", "_else"
            , "_extends", "_for", "_if", "_implements", "_int", "_interface", "_newarray"
            , "_println", "_readln", "_return", "_string", "_void", "_while", "_plus"
            , "_minus", "_multiplication", "_division", "_mod", "_less", "_lessequal"
            , "_greater", "_greaterequal", "_equal", "_notequal", "_and", "_or", "_not"
            , "_assignop", "_semicolon", "_comma", "_period", "_leftparen", "_rightparen"
            , "_leftbracket", "_rightbracket", "_leftbrace", "_rightbrace", "_intconstant"
            , "_doubleconstant", "_stringconstant", "_booleanconstant", "_id"};
    //Reserved words in Toy
    private static final String[] reserved = {"boolean", "break", "class", "double", "else"
            , "extends", "false", "for", "if", "implements", "int", "interface"
            , "newarray", "println", "readln", "return", "string", "true", "void", "while"};
    //Regular expressions for identifiers, integers, doubles, strings, operations, punctuation, and whitespace
    //Booleans are covered under identifiers and reserved words
    private static final String idRegex = "[A-Za-z_][A-Za-z0-9_]*";
    private static final String intRegex = "0[xX][0-9a-fA-F]+|\\d+";
    private static final String doubleRegex = "\\d+\\.\\d*[Ee][+-]\\d+|\\d+\\.\\d*[Ee]\\d+|\\d+\\.\\d*";
    //Recognize ASCII or Unicode parentheses
    private static final String stringRegex = "\".*?\"|“.*?”";
    private static final String opPuncRegex = "//|/\\*|\\*/|\\+|\\-|\\*|/|%|<=|>=|<|>|==|!=|&&|\\|\\||!|=|;|,|\\.|\\(|\\)|\\[|\\]|\\{|\\}|\"|“|”";
    private static final String whitespaceRegex = "[ \n\t]";
    //A combined regular expression for any possible combination of the above
    //Will work even when there are no whitespace characters in the input
    public static final String totRegex = stringRegex + '|' + idRegex + '|'
            + doubleRegex + '|' + intRegex + '|' + opPuncRegex + '|' + whitespaceRegex;

    public static String[] getReserved () {
        return reserved;
    }

    public static int getIntToken (String token) {
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].equals(token)) {
                return i;
            }
        }
        return -1;
    }

    public static String getStringToken (int i) {
        if (i < 0 || i > tokens.length - 1) {
            return "_NOT_A_TOKEN";
        }
        return tokens[i];
    }

    public static boolean isIdentifier (String s) {
        return Pattern.matches(idRegex, s);
    }

    public static boolean isReserved (String s) {
        for (int i = 0; i < reserved.length; i++) {
            if (reserved[i].equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isIntConst (String s) {
        return Pattern.matches(intRegex, s);
    }

    public static boolean isDoubleConst (String s) {
        return Pattern.matches(doubleRegex, s);
    }

    public static boolean isStringConst (String s) {
        return Pattern.matches(stringRegex, s);
    }

    public static boolean isOperatorOrPunctuation (String s) {
        return Pattern.matches(opPuncRegex, s);
    }

    public static boolean isWhitespace (String s) {
        return Pattern.matches(whitespaceRegex, s);
    }

    public static int stringToToken (String s) {
        if (isReserved(s)) {
            if (s.equals("boolean")) {
                return getIntToken("_boolean");
            } else if (s.equals("break")) {
                return getIntToken("_break");
            } else if (s.equals("class")) {
                return getIntToken("_class");
            } else if (s.equals("double")) {
                return getIntToken("_double");
            } else if (s.equals("else")) {
                return getIntToken("_else");
            } else if (s.equals("extends")) {
                return getIntToken("_extends");
            } else if (s.equals("false")) {
                return getIntToken("_booleanconstant");
            } else if (s.equals("for")) {
                return getIntToken("_for");
            } else if (s.equals("if")) {
                return getIntToken("_if");
            } else if (s.equals("implements")) {
                return getIntToken("_implements");
            } else if (s.equals("int")) {
                return getIntToken("_int");
            } else if (s.equals("interface")) {
                return getIntToken("_interface");
            } else if (s.equals("newarray")) {
                return getIntToken("_newarray");
            } else if (s.equals("println")) {
                return getIntToken("_println");
            } else if (s.equals("readln")) {
                return getIntToken("_readln");
            } else if (s.equals("return")) {
                return getIntToken("_return");
            } else if (s.equals("string")) {
                return getIntToken("_string");
            } else if (s.equals("true")) {
                return getIntToken("_booleanconstant");
            } else if (s.equals("void")) {
                return getIntToken("_void");
            } else if (s.equals("while")) {
                return getIntToken("_while");
            } else {
                //Should never happen
                return -1;
            }
        } else if (isStringConst(s)) {
            return getIntToken("_stringconstant");
        } else if (isIdentifier(s)) {
            return getIntToken("_id");
        } else if (isDoubleConst(s)) {
            return getIntToken("_doubleconstant");
        } else if (isIntConst(s)) {
            return getIntToken("_intconstant");
        } else if (isOperatorOrPunctuation(s)) {
            if (s.equals("+")) {
                return getIntToken("_plus");
            } else if (s.equals("-")) {
                return getIntToken("_minus");
            } else if (s.equals("*")) {
                return getIntToken("_multiplication");
            } else if (s.equals("/")) {
                return getIntToken("_division");
            } else if (s.equals("%")) {
                return getIntToken("_mod");
            } else if (s.equals("<")) {
                return getIntToken("_less");
            } else if (s.equals("<=")) {
                return getIntToken("_lessequal");
            } else if (s.equals(">")) {
                return getIntToken("_greater");
            } else if (s.equals(">=")) {
                return getIntToken("_greaterequal");
            } else if (s.equals("==")) {
                return getIntToken("_equal");
            } else if (s.equals("!=")) {
                return getIntToken("_notequal");
            } else if (s.equals("&&")) {
                return getIntToken("_and");
            } else if (s.equals("||")) {
                return getIntToken("_or");
            } else if (s.equals("!")) {
                return getIntToken("_not");
            } else if (s.equals("=")) {
                return getIntToken("_assignop");
            } else if (s.equals(";")) {
                return getIntToken("_semicolon");
            } else if (s.equals(",")) {
                return getIntToken("_comma");
            } else if (s.equals(".")) {
                return getIntToken("_period");
            } else if (s.equals("(")) {
                return getIntToken("_leftparen");
            } else if (s.equals(")")) {
                return getIntToken("_rightparen");
            } else if (s.equals("[")) {
                return getIntToken("_leftbracket");
            } else if (s.equals("]")) {
                return getIntToken("_rightbracket");
            } else if (s.equals("{")) {
                return getIntToken("_leftbrace");
            } else if (s.equals("}")) {
                return getIntToken("_rightbrace");
            } else {
                //Includes multiline comment ends and double quotes
                return -1;
            }
        } else {
            //Includes whitespace
            return -1;
        }
    }
}
