import java.util.*;

public class JSONParser {
    private String json;
    private int pos = 0;

    public JSONParser(String json) {
        this.json = json.trim();
    }

    public Map<String, Object> parse() {
        if (json.charAt(pos) == '{') {
            return parseObject();
        }
        throw new IllegalArgumentException("Invalid JSON input");
    }

    private Map<String, Object> parseObject() {
        Map<String, Object> result = new HashMap<>();
        pos++; // skip '{'

        while (pos < json.length()) {
            char currentChar = json.charAt(pos);

            if (currentChar == '}') {
                pos++; // skip '}'
                return result;
            } else if (currentChar == '"') {
                String key = parseString();
                pos++; // skip ':'
                Object value = parseValue();
                result.put(key, value);

                if (json.charAt(pos) == ',') {
                    pos++; // skip ','
                }
            } else {
                pos++;
            }
        }

        throw new IllegalArgumentException("Expected closing '}'");
    }

    private Object parseValue() {
        skipWhitespace();

        char currentChar = json.charAt(pos);
        if (currentChar == '"') {
            return parseString();
        } else if (currentChar == '{') {
            return parseObject();
        } else if (currentChar == '[') {
            return parseArray();
        } else if (Character.isDigit(currentChar) || currentChar == '-') {
            return parseNumber();
        } else if (json.startsWith("true", pos)) {
            pos += 4;
            return true;
        } else if (json.startsWith("false", pos)) {
            pos += 5;
            return false;
        } else if (json.startsWith("null", pos)) {
            pos += 4;
            return null;
        }

        throw new IllegalArgumentException("Unexpected value");
    }

    private List<Object> parseArray() {
        List<Object> list = new ArrayList<>();
        pos++; // skip '['

        while (pos < json.length()) {
            if (json.charAt(pos) == ']') {
                pos++; // skip ']'
                return list;
            }

            list.add(parseValue());

            if (json.charAt(pos) == ',') {
                pos++; // skip ','
            }
        }

        throw new IllegalArgumentException("Expected closing ']'");
    }

    private String parseString() {
        StringBuilder sb = new StringBuilder();
        pos++; // skip opening '"'

        while (pos < json.length()) {
            char currentChar = json.charAt(pos);
            if (currentChar == '"') {
                pos++; // skip closing '"'
                return sb.toString();
            }

            sb.append(currentChar);
            pos++;
        }

        throw new IllegalArgumentException("Unterminated string");
    }

    private Number parseNumber() {
        int start = pos;

        while (pos < json.length() && (Character.isDigit(json.charAt(pos)) || json.charAt(pos) == '.' || json.charAt(pos) == '-')) {
            pos++;
        }

        String numberStr = json.substring(start, pos);

        if (numberStr.contains(".")) {
            return Double.parseDouble(numberStr);
        } else {
            return Integer.parseInt(numberStr);
        }
    }

    private void skipWhitespace() {
        while (pos < json.length() && Character.isWhitespace(json.charAt(pos))) {
            pos++;
        }
    }

    public static void main(String[] args) {
        String jsonString = "{\"name\":\"John\",\"age\":30,\"isStudent\":false,\"scores\":[90,88,92],\"address\":{\"city\":\"New York\"}}";

        JSONParser parser = new JSONParser(jsonString);
        Map<String, Object> jsonMap = parser.parse();

        System.out.println(jsonMap);
        System.out.println(jsonString);
    }
}
