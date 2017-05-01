# Sentiment-Analysis-Project
The project is to develop an Android app which will determine the sentiment of the texts obtained from reviews from Twitter.

## Instructions
1.Before building the apk, put your Twitter Consumer and Secret Key in the MainActivity.java</br>

2.Build the apk</br>

3.Run the app on your phone</br>

4.Enter your Twitter User ID and password</br>

5.Select a topic (eg. Politics)</br>

6.Enter your search text ( eg. Donald Trump) and click Search</br>

7.Click Review to see the result</br>

## Steps Followed 
* The Apps asks for the user's twitter login id and password

* After logging in, the app asks to select a topic. Currently, there are 2 topics available to choose from ( Movies and Politics)

* On choosing the topic, the app asks for the text whose sentiment the user wants to know (eg. Barrack Obama)

* On clicking the "Search" button, 100 recent tweets are shown for the searched text

* To see the sentiment of the reviews/tweets, the user have to click on the "Review" button

* On clicking the "Review" button, the app calculates the sentiment in percentage(positive) using machine learning approach.

* Calculation involves: 
  * Tokenization of the sentiment texts
  * Removal of Texts that are not responsible for sentiment determination but used largely such as pronouns, prepositions, etc.
  * Selection of most frequent 1000 tokens
  * Construction of Histograms for every element
  * Training the model with 2/3rd data elements
  * Construction of averaged histogram for both positive and negative class
  * Testing with KNN and nBayes classifiers

## App Screenshots

<img src="https://github.com/Suvam-Mondal/Sentiment-Analysis-Project/blob/master/Screenshot_20170501-143227.png" height="480" width="320">

<img src="https://github.com/Suvam-Mondal/Sentiment-Analysis-Project/blob/master/Screenshot_20170501-143238.png" height="480" width="320">

<img src="https://github.com/Suvam-Mondal/Sentiment-Analysis-Project/blob/master/18191577_1585123341552492_2020604198_n.png" height="480" width="320">

<img src="https://github.com/Suvam-Mondal/Sentiment-Analysis-Project/blob/master/18191584_1585123338219159_601556890_n.png" height="480" width="320">
