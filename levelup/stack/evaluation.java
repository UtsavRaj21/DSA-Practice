import java.util.HashMap;
import java.util.Stack;

public class evaluation {

    // Infix Evaluation

    public int evaluate(int val1, int val2, int oper) {
        int val = 0;

        if (oper == '*') {
            val = val1 * val2;
        } else if (oper == '/') {
            val = val1 / val2;
        } else if (oper == '+') {
            val = val1 + val2;
        } else if (oper == '-') {
            val = val1 - val2;
        }
        return val;
    }

    public int priority(char ch) {
        if (ch == '*' || ch == '/') {
            return 2;
        } else if (ch == '+' || ch == '-') {
            return 1;
        }
        return 0;
    }

    public int InfixEvaluation(String s) {
        Stack<Integer> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);  
            if (ch == ' ') {
                continue;
            }
            if (ch >= '1' && ch <= '9') {
                int x = Character.getNumericValue(ch);
                operand.push(x);
            } else if (ch == '(') {
                operator.push(ch);
            } else if (ch == ')') {
                while (operator.peek() != '(') {
                    int val2 = operand.pop();
                    int val1 = operand.pop();
                    char oper = operator.pop();
                    int val = evaluate(val1, val2, oper);
                    operand.push(val);
                }
                operator.pop();

            } else {
                while (operator.size() > 0 && operator.peek() != '(' && priority(ch) <= priority(operator.peek())) {
                    int val2 = operand.pop();
                    int val1 = operand.pop();
                    char oper = operator.pop();
                    int val = evaluate(val1, val2, oper);
                    operand.push(val);
                    // System.out.println(val);
                }
                operator.push(ch);
            }
        }

        while (operator.size() > 0){
            int val2 = operand.pop();
            int val1 = operand.pop();
            char oper = operator.pop();
            int val = evaluate(val1, val2, oper);
            operand.push(val);

        }
        int res = operand.peek();
        return res;
    }

    //Infix to prefix
    public String InfixToPrefix(String s) {
        Stack<String> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);  
            if (ch == ' ') {
                continue;
            }
            if (ch >= 'a' && ch <= 'z') {
                operand.push(ch+"");
            } else if (ch == '(') {
                operator.push(ch);
            } else if (ch == ')') {
                while (operator.peek() != '(') {
                    String val2 = operand.pop();
                    String val1 = operand.pop();
                    char oper = operator.pop();
                    operand.push(oper+val1+val2);
                }
                operator.pop();

            } else {
                while (operator.size() > 0 && operator.peek() != '(' && priority(ch) <= priority(operator.peek())) {
                    String val2 = operand.pop();
                    String val1 = operand.pop();
                    char oper = operator.pop();
                    operand.push(oper+val1+val2);
                }
                operator.push(ch);
            }
        }

        while (operator.size() > 0){
            String val2 = operand.pop();
            String val1 = operand.pop();
            char oper = operator.pop();
            operand.push(oper+val1+val2);

        }
        return operand.peek();
    }

    ////Infix to postfix
    public String InfixToPostfix(String s) {
        Stack<String> operand = new Stack<>();
        Stack<Character> operator = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);  
            if (ch == ' ') {
                continue;
            }
            if (ch >= 'a' && ch <= 'z') {
                operand.push(ch+"");
            } else if (ch == '(') {
                operator.push(ch);
            } else if (ch == ')') {
                while (operator.peek() != '(') {
                    String val2 = operand.pop();
                    String val1 = operand.pop();
                    char oper = operator.pop();
                    operand.push(val1+val2+oper);
                }
                operator.pop();

            } else {
                while (operator.size() > 0 && operator.peek() != '(' && priority(ch) <= priority(operator.peek())) {
                    String val2 = operand.pop();
                    String val1 = operand.pop();
                    char oper = operator.pop();
                    operand.push(val1+val2+oper);
                }
                operator.push(ch);
            }
        }

        while (operator.size() > 0){
            String val2 = operand.pop();
            String val1 = operand.pop();
            char oper = operator.pop();
            operand.push(val1+val2+oper);

        }
        return operand.peek();
    }
    
    //PostFix Evaluation
    public int postfixEvaluation(String s){
        Stack<Integer> operand = new Stack<>();
    
        for(int i = 0 ; i < s.length() ; i++){
            char ch = s.charAt(i);
            if(ch==' '){
                continue;
            }
            if(ch>='1' && ch<='9'){
                operand.push(ch-'0');
            }else{
                int val2 = operand.pop();
                int val1 = operand.pop();
                int res = evaluate(val1, val2, ch);
                operand.push(res);
            }
        }
        return operand.peek();
    } 
    
    //Postfix to Infix

    public String postfixToInfix(String s){
        Stack<String> operand = new Stack<>();
    
        for(int i = 0 ; i < s.length() ; i++){
            char ch = s.charAt(i);
            if(ch==' '){
                continue;
            }
            if(ch>='1' && ch<='9'){
                operand.push(ch+"");
            }else{
                String val2 = operand.pop();
                String val1 = operand.pop();
                String res = '(' + val1 + ch + val2 +')' ; 
                operand.push(res);
            }
        }
        return operand.peek();
    } 

    //Postfix to Prefix

    public String postfixToPrefix(String s){
        Stack<String> operand = new Stack<>();
    
        for(int i = 0 ; i < s.length() ; i++){
            char ch = s.charAt(i);
            if(ch==' '){
                continue;
            }
            if(ch>='1' && ch<='9'){
                operand.push(ch+"");
            }else{
                String val2 = operand.pop();
                String val1 = operand.pop();
                String res = ch + val1 + val2 ; 
                operand.push(res);
            }
        }
        return operand.peek();
    } 
    
    //Prefix Evaluation
    public int PrefixEvaluation(String s){
        Stack<Integer> st = new Stack<>();

        for(int i = s.length()-1;i>=0 ;i--){
            char ch = s.charAt(i);
            if(ch==' '){
                continue;
            }
            if(ch>='1' && ch<='9'){
                int x = ch-'0';
                st.push(x);
            }else{
                int v1 = st.pop();
                int v2 = st.pop();
                int res = evaluate(v1, v2, ch);
                st.push(res);
            }
        }
        return st.peek();
    }
    
    //Prefix to Postfix
    public String PrefixToPostfix(String s){
        Stack<String> st = new Stack<>();

        for(int i = s.length()-1;i>=0 ;i--){
            char ch = s.charAt(i);
            if(ch==' '){
                continue;
            }
            if(ch>='1' && ch<='9'){
                st.push(ch+"");
            }else{
                String v1 = st.pop();
                String v2 = st.pop();
                String res = v1+v2+ch;
                st.push(res);
            }
        }
        return st.peek();
    }

    //Prefix to Infix
    public String PrefixToInfix(String s){
        String str = PrefixToPostfix(s);
        str = postfixToInfix(str);
        return str;
    }
    public static void main(String[] args) {

    }
}
