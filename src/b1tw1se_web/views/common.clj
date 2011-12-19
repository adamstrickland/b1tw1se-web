(ns b1tw1se-web.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers]))

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
              ]
              [:body
                [:div#container
                  [:header ""]
                  [:div#main {:role "main"} content]
                  [:footer ""]
                ]
                (include-js "http://ajax.googleapis.com/ajax/libs/jquery/1.6.2/jquery.min.js")
                ; (javascript-tag "window.jQuery || document.write('<script src=\"/js/libs/jquery-1.6.2.min.js\"></script>')")
                (include-js "/js/plugins.js" "/js/script.js")
                ; (javascript-tag window.gaq = [['_setAccount', 'UAXXXXXXXXX1'],['_trackPageview'],['_trackPageLoadTime']];
                ;                  Modernizr.load({
                ;                   load: ('https:' == location.protocol ? '//ssl' : '//www') + '.google-analytics.com/ga.js'
                ;                  });
                ;               )
              ]
            ))
