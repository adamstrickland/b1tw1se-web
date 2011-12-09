(ns b1tw1se-web.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]))

(defpartial layout [& content]
            (html5
              [:head
                [:meta {:charset "urf-8"}]
                [:meta {:http-equiv "X-UA-Compatible", :content "IE=edge,chrome=1"}]
                [:title "b1tw1se :: get w1se"]
                [:meta {:name "description",  :content ""}]
                [:meta {:name "author",  :content ""}]
                [:meta {:name "viewport", :content "width=device-width,initial-scale=1"}]
                ; (include-css "/css/reset.css")
                (include-css "/css/style.css")
                (include-js "/js/libs/modernizr-2.0.6.min.js")
              ]
              [:body
                [:div#wrapper
                  content
                ]
              ]
            ))
