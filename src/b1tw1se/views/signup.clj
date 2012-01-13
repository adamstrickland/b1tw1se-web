(ns b1tw1se.views.signup
  (:require [b1tw1se.views.common :as common])
  (:use noir.core
        hiccup.core
        hiccup.form-helpers
        hiccup.page-helpers)
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

 (defpage "/signup" []
 	(common/layout 
	 	[:p "Yes, we know...  A signup form.  Just do it: it keeps the riffraff out."]
	 	(form-to [:post "/register"]
	 		(label "username" "Username/pseudonym/whatever")
	 		[:br]
	 		(text-field "username")
	 		[:br]
	 		(label "password" "Password")
	 		[:br]
		 	(password-field "password")
	 		[:br]
		 	(label "password_confirm" "Confirm Password")
	 		[:br]
		 	(password-field "password_confirm")
	 		[:br]
		 	(label "code_test" "What is the answer to '5 modulo 2'?")
	 		[:br]
		 	[:input {:type "text", :name "code_test", :value nil, :id "code_test" :placeholder "Figure it out, tourist"}]
	 		[:br]
		 	(submit-button "Sign Up"))))