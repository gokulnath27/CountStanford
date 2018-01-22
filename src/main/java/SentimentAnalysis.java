import com.sun.istack.internal.localization.NullLocalizable;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;

import java.util.*;
import java.io.*;

public class SentimentAnalysis {
    public static void main(String[] args) throws IOException {
        Properties pipelineProp = new Properties();
        Properties tokenizerProp = new Properties();
        pipelineProp.setProperty("annotators", "parse, sentiment");
        pipelineProp.setProperty("parse.binaryTrees", "true");
        pipelineProp.setProperty("enforceRequirements", "false");
        tokenizerProp.setProperty("annotators", "tokenize, ssplit");
        StanfordCoreNLP tokenizer = new StanfordCoreNLP(tokenizerProp);
        StanfordCoreNLP pipeline = new StanfordCoreNLP(pipelineProp);


        File file = new File("/Users/gokul-pt1831/Downloads/Football.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            Annotation annotation = tokenizer.process(line);
            pipeline.annotate(annotation);
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
                String output = sentence.get(SentimentCoreAnnotations.SentimentClass.class);
                System.out.println(output);
            }
        }
    }
}