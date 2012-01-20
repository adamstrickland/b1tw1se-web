(ns b1tw1se.views.topics
  (:require [b1tw1se.views.common :as common]
	  		[b1tw1se.config.connection :as conn]
	        [b1tw1se.models.topic :as topic]
	        [noir.response :as response]
	        [noir.session :as session])
  (:use noir.core
        hiccup.core
        hiccup.form-helpers
        hiccup.page-helpers)
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

  ; (defpage [:post "/boards/:id/topics/create"] {:keys [id title content]}
  ; 	(topic/create title content (session/get "userid") id)
  ; 	(response/redirect (str "/boards/" id)))

  (defpage [:post "/boards/:id/topics/create"] {:keys [id title]}
    (topic/create title id)
    (response/redirect (str "/boards/" id)))
    
  (defpage "/topics/:id" {:keys [id]}
    (common/layout
      (let [t (topic/find-one id)]
        [:h2 (:title t)])))