<h1>Intellifridge</h1>
<p>December 2021</p>

<em>To the best Mother... Just toss the broccoli before it starts stinking</em>

<h3>So... How did this start?</h3>

<p>Most people have been in this situation. You go to the grocery store and try to remember everything that you need for the days ahead. When you get home with bags full of fresh food; upon opening the fridge door, you notice that food that you had pushed to the back of the fridge has started to turn. This has happened to me and my family more times than I wish to admit. One time in particular (it was boiled broccoli) the smell was so bad, it could easily be detected from a different room even though the fridge doors were closed! Of course it was up to me me to toss the sulphuric scent out of the house. That's when the idea came to my head. I didn't want to throw away bad stinking food anymore. I did not want food to be forgotten and then wasted. All the best technologies improve home life first. Maybe we can track the life of food and prevent waste by code. To slightly misquote Bigweld "See a need... Fill a need"</p>

<h3>About the name</h3>
<p>Originally, there was no cathy name planned out. It came to our group by chance in a joke. This is would be considered very nerdy humor. The editor we used throughout our class-time together was intellij. As we threw names out in our early sessions one of us commented that we were using intellij to make an intelli-fridge. The rest is history... </p>

<h3>Code up</h3>
<p>Intellifridge is the capstone project of four students in the Draco cohort in Codeup. Joseph Congdon, Serguio Landa, Paris Shirley and Salvador Salazar. This project is the culmination of everything we have learned in our intensive 22 week web development course. The technologies used to make Intellifridge were: Springboot(Java), JQuery(JavaScript), MySQL, CSS and HTML.</p> 

<p>Early on in development we had a fun time working out the concepts tracking food... Especially... How can four students make a food tracker <em>WITHOUT</em> having to pay for an expensive API? We came down to this compromise; We could input our own "shelf-life" into the database. We did not need a massive online service that included brand names, organic vs un-organic or very remote foods that few actually purchase. We set out by creating a table for the basic food groups everyone knows. Meats, Dairy, Grains, Sweets and Fruits/Vegetables. Then we stared listing our foods that we would used the most (milk, steak, eggs, cheese, broccoli etc.). We simply googled the basic shelf-life of our foods and logged them in to our database. HERE IS A VERY IMPORTANT NOTE: We had to distinguish between shelf-life and food inside of the fridge. Coming up with the correct naming convention was a real head scratcher. The user had to be able to search for the foods, and yet the user had to be able to log in the foods to his own personal fridge.

This led to another interesting problem. The user had to have the ability to add food to a fridge. The fridge had to act as a database that the user could see nad interact with. At the start of the project we also agreed that the user had the ability to create multiple fridges. Our code had to be very specific so that a user who added carrots to his first fridge would not see carrots in all of his fridges.</p>

<h3>What features make Intellifridge Different</h3>
<p>It's time for the sales pitch. Intellifridge is a food tracking kitchen "aid" if you will that helps users eliminate waste in the refrigerator. There are already smart fridges out there that track purchases and send out alerts. The only issue with them is the price tag. The intellifridge app functions the same way without the need to switch to a refrigerator worth thousands of dollars.</p>

<h3>A new user</h3>
You start off by creating a profile(a username and a password). If you wish you can add your phone number to receive text notifications on expiring food. You also have the option to add an email just in case a user wants to add you to a fridge but does not remember your username. Finally, you can add an image to make your profile stand out from other users. 

Moving from the profile we go to the fridge creation feature. When you first log in, the profile page will look empty. There is a sign that directs the user to create their first fridge. from there the user view the fridge(open it up and see what is inside), add friends to the fridge(gives anyone the access to add or delete fridges from the created fridge) or to delete/leave the fridge</p>

<h3>Inside the fridge</h3>
<p>The fridge itself is a table system made by dataTables.js. The table contains food group information as well as the ability to sort/filter through your food(by: food group, expiration date, fridge or freezer etc) In a mobile view the table converts to small card-like containers that hold basic information of the food in question such as: name, expiration and if you want to delete it.</p>

<h3>The Apis used</h3>
<p>Intellifridge is comprised of 4 different Apis: Twilio, unsplash, datatables and FileStack.

Twilio is brain behind the automated text messages. Twilio requires an account SID, an authorization token and a phone number. This API does cost after a free trial equivalent to about $15, so use sparingly.

Up next is Unsplash. finding a photo api that did not charge was an interesting challenge. We decided unsplash because it was free and the photos were in the ball-park for what we were looking for. It's not without its quirks. If you type in Turkey you get scenic views of Turkey... not the food. The same goes for lamb. There may be more, but I don't know if I have the heart to look for them. The one fix I can think of is changing the wording of our food shelf-life table. If we changed it to "sliced turkey" or "roasted lamb" I think our problems would go away ... for now. This one requires an account and a token. Watch out for your search amount. They stack up very, very quickly. 

Datatables is the structure of our fridge. It is a plug and use js table that allows for easily customizable table column categories. It works beautifully in pc view. Making it work for a phone was one of our biggest challenges.     

Finally, we have Filestack API. I like this one because it adds much needed flair to our app. This allows our user to select an image from their social media or their own files and make it their profile picture. This one also requires an API key. </p>


