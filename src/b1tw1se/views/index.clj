(ns b1tw1se.views.index
  (:require [b1tw1se.views.common :as common])
  (:use noir.core
        hiccup.core
        hiccup.page-helpers)
  (:use somnium.congomongo)
  (:use [somnium.congomongo.config :only [*mongo-config*]]))

; (defpage "/welcome-mongo" []
;   (maybe-init)
;   (let [counter 
; 	(fetch-and-modify 
; 	 :firstcollection ;; In the collection named 'firstcollection',
; 	 {:_id "counter"} ;; find the counter record.
; 	 {:$inc {:value 1} } ;; Increment it.
; 	 :return-new true :upsert? true)] ;; Insert if not there.
;     (common/layout
;      [:p (str "Welcome to noir-heroku, you're visitor " (or (:value counter) 0))])))

; (defpage "/welcome-mongo" []
;   (common/mongo-init)
;   (let [counter (fetch-and-modify :firstcollection {:_id "counter"} {:$inc {:value 1} } :return-new true :upsert? true)]
;     (common/layout [:p (str "Welcome to noir-heroku, you're visitor " (or (:value counter) 0))])))

; (defpage "/welcome-mongo" [] (common/mongo-init) (common/layout [:h1 "MONGO"]))

(defn first-time? [] true)

; !!!! THIS ONE WORKS !!!!
; (defpage "/index" []
;   (with-mongo common/conn
;     (let [counter (fetch-and-modify :firstcollection {:_id "counter"} {:$inc {:value 1}} :return-new true :upsert? true)]
;       (common/layout 
; 	      [:h1 "Welcome to b1tw1se"] 
; 	      [:h2 "a community for programmers by programmers"] 
; 	      [:p (str "You are visitor number " (or (:value counter) 0))]))))

(defpartial first-time-content []
  [:h1 "Welcome to b1tw1se"]
  [:h2 "a community for programmers, by programmers"]
  [:div#content 
    [:h3 "What is this place?"]
    [:p "In short, we felt that all the general use websites out there didn't do enough to address the developer community, so we built this."]
    [:h3 "Why should I care?"]
    [:p "To paraphrase the great Ron Swanson \"Don't be a tourist\".  If you've decided to take money from people in exchange for writing code, you've
      decided to be a programmer.  If you've decided to be a programmer, either do it or GTFO.  Why?  I want to say 'because you fuck it up for the rest of us', but why would you care about everyone else?  Rather, I'll appeal to your ego: you've decided to do something, now decide to be good with it or go find something else.  If you can't (or won't) do that...  Feel free to leave."]
    [:p "For the non-tourists, here's the deal:  the world is ours.  We've been walked on for a long time; not because they're smarter (they're not), or harder working (again, no), but because they've had a common purpose, where most of us have historically been more interested in solving some cool problems than breaking out the Machiavelli (pretty sure that's some sparkly wine, right?).  Seriously, the world is our oyster; we can - no, we will be - just fine without them, but without us, their gravy train comes to an end.  This is the first step: congregate, organize and start sharing information.  Let's get started."]
    [:h3 "Okay, what's here?"]
    [:p "At b1tw1se, we expect you to find a lot of good stuff.  Looking for some help on your *Next Big Thing*?  Chances are you'll find someone here willing to help.  Curious how much other programmers make?  Just ask.  However, as stated above, we hope b1tw1se becomes more than that.  Baby steps..."]
    [:h3 "Heck yeah, what's next?"]
    [:a.get_started {:href "/signup"} "Get Started"]])

(defpage "/index" []
  (if first-time? (common/layout (first-time-content))))