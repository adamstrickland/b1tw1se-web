(ns b1tw1se.views.old_welcome
  (:require [b1tw1se.views.common :as common] )
  (:use [noir.core :only [defpage]]
        [hiccup.core :only [html]]))

(defpage "/old_welcome" []
         (common/layout
           [:h1 "Welcome to b1tw1se"]
           [:h2 "a community by programmers for programmers"]  
         ))
