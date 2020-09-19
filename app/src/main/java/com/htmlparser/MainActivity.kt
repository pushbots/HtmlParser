package com.htmlparser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.htmlparser.adapter.ArticleAdapter
import com.htmlparser.elements.*
import com.htmlparser.source.StringSource
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate: hi")


        val source = """
            <p></p> <p>You don’t have to do a whole lot of research to realize how popular push notifications are in 2019.&nbsp;</p> <p>More often than not, all you’ll need to do is check the phone in your pocket.&nbsp;</p> <p>And why not use push notifications? They’re a great way for brands to increase engagement and pump up their sales. When done right, push notifications are not only great for the people who send them but also the people who receive them.</p> <p>According to a recent Google study, 85% of mobile users stated that they found push notifications useful and enjoyed receiving them.</p> <p>But there is a downside.</p> <p>In that same study, 27% of people who had recently abandoned apps did so due to receiving too many push notifications.&nbsp;</p> <p>But how many is too much? And what kind of content should you send? What’s the trick to sending people the push notifications they want to receive and not sending them the ones they don’t want to receive.</p> <p>For a lot of people, it’s A/B testing. It’s been the norm in email marketing for years now and it’s quickly gaining traction with marketers who work with push notifications. &nbsp; &nbsp;</p> <p>The A/B test is where a marketer takes a randomized section of his customer base and splits them into two groups. They then deliver both the A and the B group two unique ads to see which outperforms the other. After the test, the marketer then delivers the best performing ad to the rest of their customer base.</p> <p>While all of that might sound completely reliable and scientific, when you speak with experienced A/B testers they’ll tell you that the process isn’t exactly painless.&nbsp;</p> <p>It takes a lot of energy and a great eye for detail for marketers to truly understand what variables have gone into making one ad more effective than another. This means that a lot of work hours get eaten away by marketers testing single variables at a time.</p> <p>The process is slow and many walk away from it feeling like it’s more of a glorified guessing game than anything else.</p> <p>&nbsp;That’s where we come in.</p> <p>We’ve automated the process of A/B testing so people like you can consistently deliver high converting push notifications.&nbsp;</p> <center><figure class="wp-block-embed-youtube aligncenter wp-block-embed is-type-video is-provider-youtube wp-embed-aspect-4-3 wp-has-aspect-ratio"><div class="wp-block-embed__wrapper"> <span class="embed-youtube" style="text-align:center; display: block;"><iframe class='youtube-player' width='900' height='507' src='https://www.youtube.com/embed/GWimTd9u8Wg?version=3&#038;rel=1&#038;fs=1&#038;autohide=2&#038;showsearch=0&#038;showinfo=1&#038;iv_load_policy=1&#038;wmode=transparent' allowfullscreen='true' style='border:0;'></iframe></span> </div></figure></center> <p>We call it smart A/B testing. Just plug in your two sample messages to PushBots, and the bots will determine the better performing message and automatically send it to the rest of your users. You can also adjust the amount of time the bots should wait before comparing the results.</p> <p>Choosing PushBots for your A/B testing is like choosing a calculator over an abacus. You’ll notice how much more straightforward and freeing the process of A/B testing becomes when you’re given stats in real-time.&nbsp;</p> <p>With push notifications, you’re not marketing to a crowd, but a sea of individuals. Doing this can be daunting, but we feel that we’ve created a powerful tool that will truly empower marketers to get results.&nbsp;</p>
        """.trimIndent()

        val adapter = ArticleAdapter()
        recycler.adapter = adapter
        recycler.setItemViewCacheSize(20)
        HtmlParser.Builder(StringSource(source))
            .setCallback(object : HtmlParser.ParserCallbacks {
                override fun onParseFinished(list: List<Element>) {
                    adapter.submitList(list)
                    list.forEach {
                        Log.d(TAG, "onParseFinished: ${ElementType.values()[it.type]} ||| ${it.toString()}")
                        if (it is ImageElement) {
                            Log.d(TAG, "onImageFound: $it ||| ${it.ImageUrl}")
                        } else if (it is IFrameElement) {
                            Log.d(TAG, "onIFrameFound: $it ||| ${it.url}")
                        } else if (it is BlockQuoteElement) {
                            Log.d(TAG, "onBlockQuoteFound: ${it.data} ${it.text}")
                        } else if (it is FigureElement) {
                            Log.d(TAG, "onFigure: ${it.caption}  ${it.url}")
                        }
                    }
                }

                override fun onParseError(exception: Exception) {}

            }).build()
    }


}