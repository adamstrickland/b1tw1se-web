(ns b1tw1se.views.main
  (:require [b1tw1se.views.common :as common]
	  		[b1tw1se.config.connection :as conn]
	        [b1tw1se.models.board :as board])
  (:use noir.core
        hiccup.core
        hiccup.form-helpers
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


; (defpage "/index" []
;   (with-mongo common/conn
;     (let [counter (fetch-and-modify :firstcollection {:_id "counter"} {:$inc {:value 1}} :return-new true :upsert? true)]
;       (common/layout 
; 	      [:h1 "Welcome to b1tw1se"] 
; 	      [:h2 "a community for programmers by programmers"] 
; ; 	      [:p (str "You are visitor number " (or (:value counter) 0))]))))

(defpage "/main" []
	(defn board-row [b]
		(let [t (first (:topics b))
			  p (first (:posts t))
			  a (:author p)]
			[:tr
				[:th.topic (link-to (str "/boards/" (:_id b)) (:title b))]
				[:td.activity 
					[:div.topic_heading (link-to (str "/boards/" (:_id b) "/threads/" (:_id t)) (:title t))]
					[:div.topic_byline (link-to (str "/accounts/" (:_id a) "/profile") (:first-name a)) ", " (:created_at p)]]
				[:td.stats 
					[:div.topic_thread_stats "123" " threads: " "456" " active, " "3" " recently"]
					[:div.topic_post_stats "11" " posts: " "989" " recent"]]]))
	(common/layout
		(javascript-tag "
			$(document).ready(function(){
	 			$('#new_board_activator').click(function(){
	 				$('#new_board_control').fadeIn();
	 			});
	 		});
	 	")
	  	(identity-block)
	  	(navigation-block)
	  	[:div#boards
	  		[:table#listing 
	  			[:thead
	  				[:tr
	  					[:th "Board"]
	  					[:th "Topic"]
	  					[:th "Stats"]]]
	  			[:tbody
	  				(let [boards (board/find-fake)]
		  				(map board-row boards))]]
		  	[:div#admincontrol
		  		[:a#new_board_activator {:href "#"} "New Board"]
		  		[:div#new_board_control {:style "display:none"}
			  		(form-to [:post "/boards/create"]
			  			(label "title" "Title")
			  			[:br]
			  			(text-field "title")
			  			(submit-button "Create"))] ]]))
