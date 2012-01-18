(ns b1tw1se.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.core]
        [hiccup.page-helpers])
  (:use [somnium.congomongo])
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

(defpartial layout [& content]
            (html5
              [:head
                [:meta {:charset "urf-8"}]
                [:meta {:http-equiv "X-UA-Compatible", :content "IE=edge,chrome=1"}]
                [:title "b1tw1se :: get w1se"]
                [:meta {:name "description",  :content ""}]
                [:meta {:name "author",  :content ""}]
                [:meta {:name "viewport", :content "width=device-width,initial-scale=1"}]
                (include-css "/css/style.css" "/css/noir.css")
                (include-js "/js/libs/modernizr-2.0.6.min.js")
                (include-js "http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js")
                ; (javascript-tag "window.jQuery || document.write('<script src=\"/js/libs/jquery-1.7.1.min.js\"></script>')")
                (include-js "/js/plugins.js" "/js/script.js")
              ]
              [:body
                [:div#container
                  [:header ""]
                  [:div#main {:role "main"} content]
                  [:footer ""]
                ]
                ; (javascript-tag window.gaq = [['_setAccount', 'UAXXXXXXXXX1'],['_trackPageview'],['_trackPageLoadTime']];
                ;                  Modernizr.load({
                ;                   load: ('https:' == location.protocol ? '//ssl' : '//www') + '.google-analytics.com/ga.js'
                ;                  });
                ;               )
              ]
            ))


