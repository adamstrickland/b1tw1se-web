(ns b1tw1se-web.views.main
  (:require [b1tw1se-web.views.common :as common])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers)
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

(defpartial identity-block []
  	[:div#identity 
  		[:div#welcome "Welcome " (link-to "/account/42" "Adam")]
  		[:div#gate (link-to "/signout" "Sign Out")]])

(defpartial navigation-block []
	[:ul.navigation
		[:li.item (link-to "/main" "Main")]
		[:li.item (link-to "/blah" "Blah")]])

(defpage "/main" []
	(common/layout
	  	(identity-block)
	  	(navigation-block)
	  	[:div#boards
	  		[:table#listing 
	  			[:thead
	  				[:tr
	  					[:th "Topic"]
	  					[:th "Activity"]
	  					[:th "Stats"]]]
	  			[:tbody
	  				[:tr
	  					[:th.topic (link-to "/boards/1" "Salary")]
	  					[:td.activity 
	  						[:div.topic_heading (link-to "/boards/1/threads/13" "How much do you make?")]
	  						[:div.topic_byline (link-to "/accounts/42/profile" "Adam Strickland") ", 9-Jan-2012"]
	  					[:td.stats 
	  						[:div.topic_thread_stats "27 threads: 12 active, 2 recently"]
	  						[:div.topic_post_stats "493 posts: 17 recent"]]]]]]]))