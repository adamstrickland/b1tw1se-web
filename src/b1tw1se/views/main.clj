(ns b1tw1se.views.main
  (:require [b1tw1se.views.common :as common])
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


; (defpage "/index" []
;   (with-mongo common/conn
;     (let [counter (fetch-and-modify :firstcollection {:_id "counter"} {:$inc {:value 1}} :return-new true :upsert? true)]
;       (common/layout 
; 	      [:h1 "Welcome to b1tw1se"] 
; 	      [:h2 "a community for programmers by programmers"] 
; ; 	      [:p (str "You are visitor number " (or (:value counter) 0))]))))

; (defrecord account [_id first-name last-name])

; (defrecord post [_id author created_at content])

; (defrecord thread [
; 	_id
; 	title
; 	posts])

; (defrecord board [
; 	_id 
; 	title 
; 	threads])

(defpage "/main" []
	(defn board-row [b]
		(let [t (first (:threads b))]
			[:tr
				[:th.topic (link-to (str "/boards/" (:_id b))) (:title b)]
				[:td.activity 
					[:div.topic_heading (link-to (str "/boards/" (:_id b) "/threads/" (:_id t)) (:title t))]
					[:div.topic_byline (link-to (str "/accounts/" (:_id (:author (first (:posts t)))) "/profile") (:first-name (:author (first (:posts t))))) ", " (:created_at (first (:posts t)))]]
				[:td.stats 
					[:div.topic_thread_stats "123" " threads: " "456" " active, " "3" " recently"]
					[:div.topic_post_stats "11" " posts: " "989" " recent"]]]))
	(with-mongo common/conn
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
		  				(let [boards [
			  				(board. 1 "Salary & Stuff" [
			  					(thread. 1 "How much do you make?" [
			  						(post. 1 (account. 1 "Adam" "Strickland") "2012-01-01" "This year, with bonus, I'll pull in $155K (that's $140K salary + ~ $15K bonus)")])])
			  				]]
		  				; (let [boards (fetch :boards)]
			  				(map board-row boards))]]])))

; (defpage "/main" []
; 	(def f [board]
; 		(let [id (:_id board) boardname (:name board) tthread (first (:threads board)) ttaccount (:account tthread)]
; 		[:tr
; 				[:th.topic (link-to (str "/boards/" id) boardname)]
; 				[:td.activity 
; 					[:div.topic_heading (link-to (str "/boards/" id "/threads/" (:_id tthread)) (:title tthread))]
; 					[:div.topic_byline (link-to (str "/accounts/" (:_id ttaccount) "/profile") (:name ttaccount)) ", " (:created_at tthread)]]
; 				[:td.stats 
; 					[:div.topic_thread_stats (:count (:threads board)) " threads: " (:count (:active? (:threads board))) " active, " (:count (:recent? (:active? (:threads board)))) " recently"]
; 					[:div.topic_post_stats (:count (:posts board)) " posts: " (:count (:recent? (:posts board))) " recent"]]]))
; 	(with-mongo common/conn
; 		(common/layout
; 		  	(identity-block)
; 		  	(navigation-block)
; 		  	[:div#boards
; 		  		[:table#listing 
; 		  			[:thead
; 		  				[:tr
; 		  					[:th "Topic"]
; 		  					[:th "Activity"]
; 		  					[:th "Stats"]]]
; 		  			[:tbody
; 						(let [boards (fetch :boards)]
; 							(apply f boards))]]])))