/** A class for palindrome operations. */
public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> characterDeque = new ArrayDeque<Character>();
        char[] cs = word.toCharArray();
        for (char c : cs) {
            characterDeque.addLast(c);
        }
        return characterDeque;
    }

    public boolean isPalindrome(String word) {
        /* No deque solution:
        char[] cs = word.toCharArray();
        String word_reversed = "";
        for (int i = cs.length - 1; i > -1; i--) {
            word_reversed += cs[i];
        }
        return word.equals(word_reversed);*/
        return dequePalindrome(wordToDeque(word));
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        return dequePalindrome(wordToDeque(word), cc);
    }

    private boolean dequePalindrome(Deque<Character> q) {
        if (q.size() == 0 || q.size() == 1) {
            return true;
        }
       if (q.removeFirst() != q.removeLast() ) {
           return false;
       } else {
           return dequePalindrome(q);
       }
    }

    private boolean dequePalindrome(Deque<Character> q, CharacterComparator cc) {
        if (q.size() == 0 || q.size() == 1) {
            return true;
        }
        if (!cc.equalChars(q.removeFirst(), q.removeLast())) {
            return false;
        } else {
            return dequePalindrome(q, cc);
        }
    }
}
