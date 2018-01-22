import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;

import java.io.FileReader;
import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        int wordCount = 0;
        String path = "/Users/gokul-pt1831/Downloads/Football.txt";
        MaxentTagger tagger = new MaxentTagger(
                "/Users/gokul-pt1831/Downloads/stanford-postagger-full-2017-06-09/models/wsj-0-18-left3words-distsim.tagger");
        PTBTokenizer<CoreLabel> ptbt = new PTBTokenizer<CoreLabel>(new FileReader(path),
                new CoreLabelTokenFactory(), "");
        while (ptbt.hasNext()) {


            CoreLabel label = ptbt.next();

            String Tokens = label.toString();


            String tagged = tagger.tagString(Tokens);

            String substring = tagged.substring(Math.max(tagged.length() - 4, 0));
            int a = tagger.getTagIndex(tagged);

            // System.out.println(a);
            String compare = "CC";

            /*
            System.out.println(tagger.getTag(10));
            System.out.println(tagged);
            System.out.println(a);
            */
            if (substring.trim().equals(compare))
                continue;
            else
                wordCount++;

            //System.out.println(wordCount);
        }
        System.out.println(wordCount);
    }
}