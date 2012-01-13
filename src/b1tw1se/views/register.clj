(ns b1tw1se.views.register
  (:require [b1tw1se.views.common :as common])
  (:use [noir.core]
        [hiccup.core]
        [hiccup.page-helpers])
  (:use [somnium.congomongo])
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

 (defpage [:post "/register"] {:keys [username password password_confirm code_test]}
 	(common/layout
	 	[:h2 "Awesome!  Welcome, soldier!"]
	 	[:script {:type "text/javascript"} "
	 		$(document).ready(function(){
	 			window.location.replace(\"/main\");
	 		});
 		"]))