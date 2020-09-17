package com.htmlparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.htmlparser.elements.Element
import com.htmlparser.elements.IFrameElement
import com.htmlparser.elements.ImageElement
import com.htmlparser.source.StringSource

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: hi")
        val source = """
            <p><img loading="lazy" src="https://248am.com//images/2020/06/rocket3.jpg" alt="" width="1000" height="667" class="alignnone size-full wp-image-254857" srcset="https://248am.com//images/2020/06/rocket3-200x133.jpg 200w, https://248am.com//images/2020/06/rocket3-400x267.jpg 400w, https://248am.com//images/2020/06/rocket3-490x327.jpg 490w, https://248am.com//images/2020/06/rocket3-600x400.jpg 600w, https://248am.com//images/2020/06/rocket3-768x512.jpg 768w, https://248am.com//images/2020/06/rocket3-800x534.jpg 800w, https://248am.com//images/2020/06/rocket3.jpg 1000w" sizes="(max-width: 1000px) 100vw, 1000px" />
</p> <p><a href="https://248am.com/mark/shopping/ordering-an-espresso-machine-online/">Back in June</a>, I posted about how I was able to order a new espresso machine straight from Italy without having to pay crazy shipping prices. I briefly mention what I ended up buying but since I still hadn&#8217;t had the machine for that long, I decided I&#8217;d leave the proper review for another time and since it&#8217;s now been a few months here is what I think of my setup.</p>
 <p>Firstly my coffee machine set up is composed of the following three main parts:<br /> <a href="https://www.espressocoffeeshop.com/brands/rocket/rocket-coffee-machines/rocket-appartamento">Rocket Appartamento</a><br /> <a href="https://www.espressocoffeeshop.com/brands/rocket/rocket-coffee-grinders/Rocket-Faustino-Coffee-Grinder">Rocket Faustino Grinder</a><br /> <a href="https://www.espressocoffeeshop.com/Acaia-Lunar-Scale">Acaia Lunar Scale</a></p> 
<p>These three items along with my <a href="https://www.espressocoffeeshop.com/accessoreies-coffee-machine/coffee-tampers/motta_tampers?product_id=1249">leveling tool</a> (instead of a <a href="https://www.seattlecoffeegear.com/rocket-stainless-steel-tamper-58mm">coffee tamper</a>) have proven to be a dream team for me. One of the hardest things to get right is consistent coffee on a daily basis. There are too many factors in play and if just one of the steps changes slightly you end up with a different tasting coffee. This is why people generally have a favorite barista at their favorite coffee place, people want their coffee done the same way every morning. My setup is allowing me to get the same result every time and so I&#8217;m loving it.</p> 
<p><img loading="lazy" src="https://248am.com//images/2020/06/rocket2.jpg" alt="" width="1000" height="667" class="alignnone size-full wp-image-254858" srcset="https://248am.com//images/2020/06/rocket2-200x133.jpg 200w, https://248am.com//images/2020/06/rocket2-400x267.jpg 400w, https://248am.com//images/2020/06/rocket2-490x327.jpg 490w, https://248am.com//images/2020/06/rocket2-600x400.jpg 600w, https://248am.com//images/2020/06/rocket2-768x512.jpg 768w, https://248am.com//images/2020/06/rocket2-800x534.jpg 800w, https://248am.com//images/2020/06/rocket2.jpg 1000w" sizes="(max-width: 1000px) 100vw, 1000px" /></p>
 <p>My previous espresso machine was <a href="https://248am.com/mark/reviews/meet-the-oracle-by-breville/">the Breville Oracle</a> which had basically a fully automated process to make my latte. My Rocket setup is fully manual so I have the following steps I need to take:</p> <ul> <li>I first fill up my milk jug with milk and place it next to my machine</li> <li>I remove the <a href="https://goldkeyespresso.com/heck-portafilter-portafilter-facts-explained/">portafilter</a> from the coffee machine</li> <li>I place portafilter on my scale and zero it</li> <li>I then grind 18g of coffee into the portafilter. My grinder has a timer and I&#8217;ve set it to grind for 12.7 seconds which generally grinds around 18g of coffee</li> <li>I then place portafilter back on my scale to see if I hit 18g. I&#8217;m fine with 18-18.3g of coffee, if its a bit more I remove some, if it&#8217;s less I add more</li> <li>Using my leveling tool I press it hard on my portafilter and rotate it 8 times</li> <li>I then purge the steamer to let out condensation while also purging the <a href="https://knowyourgrinder.com/what-is-a-group-head-and-how-does-it-work/">group head</a> to stabilize the water temperature</li> <li>I mount the portafilter back in the machine, I place my scale under the portafilter and place my coffee cup on it and zero the scale</li> <li>I then take my milk jug and start steaming my milk, once the milk gets warm I start the coffee-making process</li> <li>My scale can sense when coffee drips into the cup, it then automatically starts a timer while weighing my coffee. </li> <li>In the middle of this process, my milk hits the correct temperature (I can now feel it with my hands but I started off using a thermometer) so I turn off the steamer, clean the wand and purge it to clear any milk that might have gotten inside</li> <li>I then get back to focusing on my coffee. Once I get 36 grams of coffee in my cup I stop the machine. I try to get 36g of coffee in around 20-24 seconds</li> <li>I then take my milk and pour it into my coffee cup attempting latte art which I&#8217;m consistently terrible in and then I&#8217;m done</li> </ul> <p><img loading="lazy" src="https://248am.com//images/2020/06/rocket4.jpg" alt="" width="1000" height="667" class="alignnone size-full wp-image-254886" srcset="https://248am.com//images/2020/06/rocket4-200x133.jpg 200w, https://248am.com//images/2020/06/rocket4-400x267.jpg 400w, https://248am.com//images/2020/06/rocket4-490x327.jpg 490w, https://248am.com//images/2020/06/rocket4-600x400.jpg 600w, https://248am.com//images/2020/06/rocket4-768x512.jpg 768w, https://248am.com//images/2020/06/rocket4-800x534.jpg 800w, https://248am.com//images/2020/06/rocket4.jpg 1000w" sizes="(max-width: 1000px) 100vw, 1000px" /></p> <p>It&#8217;s a lot of steps but it&#8217;s become second nature and I timed the process and from start to finish and it takes just 2 minutes. 2 minutes to make a great latte, that&#8217;s really not bad at all. Most importantly is how good my coffee is and it&#8217;s always the same. I&#8217;m making such good coffee now I&#8217;ve stopped having coffee completely outside my house. When I had the Oracle I had consistency issues all the time and used to still have coffee at %Arabica on weekends because their coffee just tasted better than mine at home. But now I&#8217;m basically making the same quality at home with my set up so I haven&#8217;t had a single coffee outside since I got the machine. Not exaggerating either, not one single coffee outside my home since I got the machine back in May or June (whenever I got my machine). No more inconsistent coffees because of different baristas, no more complaining about the temperature of the milk because they&#8217;ve either boiled it too hot or not heated it enough. I have the exact same coffee every single time.</p> <p><iframe width="560" height="315" src="https://www.youtube.com/embed/GIebBFRrunM" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe></p> <p>My coffee experience has improved tremendously because of my setup and I&#8217;m using the Appartamento model which is the entry-level Rocket machine. There are a lot more sophisticated models with more capabilities, but for my needs, the Appartamento has been more than enough and great value. The only negatives I really have are the following:</p> <ul> <li>I wish the drip tray was bigger in the Appartamento</li> <li>I wish the Appartamento could hold more water</li> <li>The Faustino grinder touch screen is great unless your fingers are damp and then it doesn&#8217;t work</li> </ul> <p><img loading="lazy" src="https://248am.com//images/2020/06/rocket1.jpg" alt="" width="1000" height="667" class="alignnone size-full wp-image-254859" srcset="https://248am.com//images/2020/06/rocket1-200x133.jpg 200w, https://248am.com//images/2020/06/rocket1-400x267.jpg 400w, https://248am.com//images/2020/06/rocket1-490x327.jpg 490w, https://248am.com//images/2020/06/rocket1-600x400.jpg 600w, https://248am.com//images/2020/06/rocket1-768x512.jpg 768w, https://248am.com//images/2020/06/rocket1-800x534.jpg 800w, https://248am.com//images/2020/06/rocket1.jpg 1000w" sizes="(max-width: 1000px) 100vw, 1000px" /></p> <p>Honestly, these three issues are very minor and aren&#8217;t really issues. So yeah, obviously I would highly recommend my setup especially if you want to make great espresso at home but don&#8217;t want to invest crazy money. My setup including accessories which I didn&#8217;t list above cost me around KD700 shipped to Kuwait. Sounds a lot but keep in mind it costs me around 500fils to make a latte at home using my favorite beans from %Arabica (Arabica Blend) and with lacto free milk (which is more expensive than normal milk). A regular latte at a coffee shop costs around KD1.750 with regular milk. I have three lattes a day so that&#8217;s a saving of 3.750KD a day or 112.500KD a month. So in just seven months of use, I&#8217;ll recoup the cost of my setup and you could recoup it even faster by using cheaper beans and milk. So you&#8217;re paying this much money upfront, but saving so much more in the long run.</p> <p>So if you want to now buy a coffee machine online, <a href="https://248am.com/mark/shopping/ordering-an-espresso-machine-online/">check out my previous post here.</a></p> 
        """.trimIndent()

        HtmlParser.Builder(StringSource(source))
            .setCallback(object : HtmlParser.ParserCallbacks {
                override fun onParseFinished(list: List<Element>) {
                    list.forEach {
                        Log.d(TAG, "onParseFinished: ${it.toString()}")
                        if (it is ImageElement) {
                            Log.d(TAG, "onImageFound: ${it.ImageUrl}")
                        } else if (it is IFrameElement) {
                            Log.d(TAG, "onIFrameFound: $it  ${it.url}")
                        }
                    }
                }

                override fun onParseError(exception: Exception) {

                }

            }).build()
    }
}