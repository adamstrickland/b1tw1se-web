(ns b1tw1se-web.views.welcome
  (:require [b1tw1se-web.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/welcome" []
         (common/layout
           [:h1 "Welcome to b1tw1se"]
           [:h2 "a community by programmers for programmers"]  
         ))
