(ns b1tw1se-web.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page-helpers :only [include-css html5]]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "b1tw1se-web"]
               (include-css "/css/reset.css")]
              [:body
               [:div#wrapper
                content]]))
