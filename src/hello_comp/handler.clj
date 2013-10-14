(ns hello-comp.handler
  (:use compojure.core
        hello-comp.db
        hello_comp.views.displays
        ring.util.response
        ring.middleware.session
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [monger.core :as mg]))

;; using MongoOptions allows fine-tuning connection parameters,
;; like automatic reconnection (highly recommended for production environment)
(let [^MongoOptions opts (mg/mongo-options :threads-allowed-to-block-for-connection-multiplier 300)
      ^ServerAddress sa  (mg/server-address "127.0.0.1" 27017)]
  (mg/connect! sa opts))


(defroutes app-routes
  (GET "/" {session :session}
      (let [count   (:count session 0)
        session (assoc session :count (inc count))]
    (-> (response (indexpage count))
        (assoc :session session))))

  (GET ["/skillform"] {{jb :JBoss jv :Java} :params} (str "the jbeezy is " jb " and the javaaa is " jv))

  ;;(GET "/insert" []
     ;;  (insertDoc)
      ;; (index-page))

  (GET "/login" []
       (loginpage true))

  (route/resources "/")
  (route/not-found "Not Found"))


(def app
  (->(handler/site app-routes)
     (wrap-session {:cookie-attrs {:max-age 60 :secure true}})
     (wrap-base-url)))



 ;; not in use might use it as an example
  (GET ["/user/:id", :id #"[0-9]+"] {{id :id} :params}
       (str "<h1>Hello user   " id "</h1>"))
