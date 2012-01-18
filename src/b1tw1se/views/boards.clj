(ns b1tw1se.views.boards
  (:require [b1tw1se.views.common :as common]
	  		[b1tw1se.config.connection :as conn]
	        [b1tw1se.models.board :as board]
	        [noir.response :as response])
  (:use noir.core
        hiccup.core
        hiccup.form-helpers
        hiccup.page-helpers)
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))


 ; (defpage [:post "/register"] {:keys [username password password_confirm code_test]}
 ; 	(common/layout
	;  	[:h2 "Awesome!  Welcome, soldier!"]
	;  	[:script {:type "text/javascript"} "
	;  		$(document).ready(function(){
	;  			window.location.replace(\"/main\");
	;  		});
 ; 		"]))

(defpage [:post "/boards/create"] {:keys [title]}
	(board/create title)
	(response/redirect "/main"))

(defpage "/boards/:id" {:keys [id]}
	(common/layout
		(javascript-tag "
			$(document).ready(function(){
	 			$('#new_topic_activator').click(function(){
	 				$('#new_topic_control').fadeIn();
	 			});
	 		});
	 	")
	  	(identity-block)
	  	(navigation-block)
		(let [board (board/find-one id)]
			[:h2 (:title board)]
			[:div#boardcontrols
				[:a#new_topic_activator {:href "#"} "New Topic"]
				[:div#new_topic_control {:style "display:none"}
					(form-to [:post (str "/boards/" id "/topics/create"]
						(label "title" "Title")
						[:br]
						(text-field "title")
						[:br]
						(label "content" "Content")
						[:br]
						(text-area "content")
						[:br]
						(submit-button "Create"))]]
			[:div#topics "TOPICS GO HERE"])))