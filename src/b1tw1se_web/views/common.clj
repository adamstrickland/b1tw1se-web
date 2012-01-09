(ns b1tw1se-web.views.common
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


(defn split-mongo-url [url]
  "Parses mongodb url from heroku, eg. mongodb://user:pass@host:port/db"
  (let [matcher (re-matcher #"^.*://(.*?):(.*?)@(.*?):(\d+)/(.*)$" url)]
    (when (.find matcher)
      (zipmap [:match :user :pass :host :port :db] (re-groups matcher)))))

; (defn gather-mongo-urls
;   (filter #(not (nil? %)) (conj (map #(get (System/getenv) %) ["MONGOHQ_URL" "MONGOLAB_URI"]) "mongodb://localhost:27017/b1tw1se")))

(defn find-heroku-urls []
  (map #(get (System/getenv) %) ["MONGOHQ_URL" "MONGOLAB_URI"]))

(defn using-heroku-url? [] (not (nil? (first (find-heroku-urls)))))

; (defn init-mongo
;   [mongo-url]
;   (let [config (split-mongo-url mongo-url)]
;     (println "Initializing MongoDB @ " mongo-url)
;     (mongo! :db (:db config) :host (:host config) :port (Integer. (:port config)))
;     (authenticate (:user config) (:pass config))))

; (defn init-mongo
;   [mongo-url]
;   (let [config (split-mongo-url mongo-url)]
;     (println "Initializing MongoDB @ " mongo-url)
;     (mongo! :db (:db config) :host (:host config) :port (Integer. (:port config)))
;     (authenticate (:user config) (:pass config))
;     (or (collection-exists? :firstcollection) (create-collection! :firstcollection))))

; (def conn 
;   (make-connection "mydb"
;                    :host "127.0.0.1"
;                    :port 27017))
; (set-connection! conn)

(defn connect-local-db []
  (let []
    (println "Initializing local MongoDB")
    ; (make-connection "b1tw1se"
    ;                  :host "127.0.0.1"
    ;                  :port 27017)
    (mongo! :db "b1tw1se" :host "127.0.0.1" :port 27017)))

(defn connect-heroku-db [mongo-url]
  (let [config (split-mongo-url mongo-url)]
    (println "Initializing MongoDB @ " mongo-url)
    (mongo! :db (:db config) :host (:host config) :port (Integer. (:port config)))
    (authenticate (:user config) (:pass config))))

(defn mongo-init []
  (when (not (connection? *mongo-config*))
    (if (using-heroku-url?) (connect-heroku-db (first (find-heroku-urls)) (connect-local-db)))))
;     (init-mongo (first gather-mongo-urls))))

; (defn mongo-init []
;   (when (not (connection? *mongo-config*))
;     (init-mongo (first ["mongodb://localhost:27017/b1tw1se"]))))

(def conn (make-connection "b1tw1se" :host "127.0.0.1" :port 27017))



; (defn maybe-init []
;   "Checks if connection and collection exist, otherwise initialize."
;   (when (not (connection? *mongo-config*))
;     (let [mongo-url (get (System/getenv) "MONGOHQ_URL") config (split-mongo-url mongo-url)]
;       (println "Initializing mongo @ " mongo-url)
;       (mongo! :db (:db config) :host (:host config) :port (Integer. (:port config)))
;       (authenticate (:user config) (:pass config))
;       (or (collection-exists? :firstcollection)
;         (create-collection! :firstcollection)))))

