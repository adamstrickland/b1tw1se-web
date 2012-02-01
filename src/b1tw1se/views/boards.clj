(ns b1tw1se.views.boards
  (:require [b1tw1se.views.common :as common]
	[b1tw1se.config.database :as conn]
	[b1tw1se.models.board :as board]
	[noir.response :as response])
  (:use noir.core
	hiccup.core
	hiccup.form-helpers
	hiccup.page-helpers)
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

(defpage [:post "/boards/create"] {:keys [title]}
	(board/create title)
	(response/redirect "/main"))

(defpage "/boards/:id" {:keys [id]}
	(defn topic-row [t]
		(let [p (first (:posts t))
			  a (:author p)]
			[:tr
				[:th.topic (link-to (str "/topics/" (:_id t)) (:title t))]]))
	(common/layout
		(javascript-tag "
			$(document).ready(function(){
	 			$('#new_topic_activator').click(function(){
	 				$('#new_topic_control').fadeIn();
	 			});
	 		});
	 	")
	  	(common/identity-block)
	  	(common/navigation-block)
		(let [brd (board/find-one id)]
			(html [:h2 (:title brd)]
				[:div#boardcontrols
					[:a#new_topic_activator {:href "#"} "New Topic"]
					[:div#new_topic_control {:style "display:none"}
						(form-to [:post (str "/boards/" (:_id brd) "/topics/create")]
							(label "title" "Title")
							[:br]
							(text-field "title")
							[:br]
							(label "content" "Content")
							[:br]
							(text-area "content")
							[:br]
							(submit-button "Create"))]]
				[:div#topics
					[:table#topics
						[:thead
							[:tr
								[:th#title "Title"]]]
						[:tbody
							(map topic-row (:topics brd))]]]))))
