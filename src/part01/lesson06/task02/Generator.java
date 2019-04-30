package part01.lesson06.task02;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Generator {

    private String[] words;
    private String[] endOfSentence = {"." + " ", "!" + " ", "?" + " "};
    private Random random = new Random();
    private static final int MAXN1 = 16;
    private static final int MAXN2 = 16;
    private static final int MAXN3 = 21;
    private int count = 0;
    private int size = 0;
    private double probability;

    public void getFiles(String path, int n, int size, String[] words, double probability) {
        this.size = size;
        this.words = words;
        this.probability = probability;
        for (int i = 0; i < n; i++) {
            createFile(path);
        }
    }

    /**
     * n1 - слов в предложении   [1..15]
     * n2 - букв в слове         [1..15] A ___, ___ ____.!?""
     * n3 - предложений в абзаце [1..20] в конце разрыв строки и перенос каретки
     * n4 - размер words         [1..1000] верть вхождения одного из words в след предложение 1/probability.
     * create one file with text
     *
     * @param path
     */
    private void createFile(String path) {

        StringBuilder text = new StringBuilder();
        int numOfParagraphs = getRandom(1, MAXN3);
        for (int i = 0; i < numOfParagraphs; i++) {
            StringBuilder str = doParagraph();
            text.append(str, 0, min(str.length(), size - text.length()));
        }
        text.delete(text.length() - 1, text.length());
        text.append(".");
        text.append("\n" + text.length());
        File file = new File(path + count++ + ".txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(text.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * create one paragraph
     *
     * @return
     */
    private StringBuilder doParagraph() {
        int numOfSentences = getRandom(1, MAXN3);
        StringBuilder paragraph = new StringBuilder();
        for (int i = 0; i < numOfSentences; i++) {
            paragraph.append(doSentence());
        }
        paragraph.append("\n");
        return paragraph;
    }

    /**
     * create one sentence
     *
     * @return
     */
    private StringBuilder doSentence() {
        int numOfWords = getRandom(1, MAXN1);
        StringBuilder sentence = new StringBuilder();
        String startOfSentence = doWord();
        startOfSentence = startOfSentence.substring(0, 1).toUpperCase() + startOfSentence.substring(1);
        sentence.append(startOfSentence);
        int indexForInsertWordFromWords = getRandom(1, numOfWords);
        for (int i = 1; i < numOfWords; i++) {
            sentence.append(random.nextBoolean() ? ", " : " ");
            if (i == indexForInsertWordFromWords && random.nextDouble() < probability) {
                sentence.append(words[random.nextInt(words.length)]);
            } else {
                sentence.append(doWord());
            }
        }
        sentence.append(endOfSentence[random.nextInt(3)]);
        return sentence;
    }

    /**
     * create one word
     *
     * @return
     */
    private String doWord() {
        int numOfSymbols = getRandom(1, MAXN2);
        char[] word = new char[numOfSymbols];
        for (int i = 0; i < numOfSymbols; i++) {
            word[i] = (char) (getRandom('a', 26));
        }
        return new String(word);
    }

    private int getRandom(int left, int right) {
        return left + random.nextInt(right);
    }

    private int min(int a, int b) {
        return a < b ? a : b;
    }
}