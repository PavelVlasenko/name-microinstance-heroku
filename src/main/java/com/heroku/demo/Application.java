/*
 * Copyright 2015 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.heroku.demo;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.util.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class Application {

   public  static    AbstractSequenceClassifier asc;


    public static void main(String[] args) {
        try {
            Properties props = new Properties();
            String loadFile = "english.all.3class.distsim.crf.ser.gz";

            asc = CRFClassifier.getClassifier(loadFile, props);

            SpringApplication.run(Application.class, args);
        }
        catch (Exception e) {
            System.out.print("Exception");
        }
    }

}
