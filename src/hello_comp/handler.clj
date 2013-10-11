(ns hello-comp.handler
  (:use compojure.core
        hello-comp.db
        hello_comp.views.displays
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [monger.core :as mg]))

(defroutes app-routes
  (GET "/" [] (show-form-simple))

  (GET ["/skillform", :id] {{bb :id} :params} (print bb))

  (GET "/insert" []
       (insertDoc)
       (index-page))

  (GET "/getAll" []
       (println (returnAll)))

  (route/resources "/")
  (route/not-found "Not Found"))


;; using MongoOptions allows fine-tuning connection parameters,
;; like automatic reconnection (highly recommended for production environment)
(let [^MongoOptions opts (mg/mongo-options :threads-allowed-to-block-for-connection-multiplier 300)
      ^ServerAddress sa  (mg/server-address "127.0.0.1" 27017)]
  (mg/connect! sa opts))


(def app
  (->(handler/site app-routes)
     (wrap-base-url)))



 ;; not in use might use it as an example
  (GET ["/user/:id", :id #"[0-9]+"] {{id :id} :params}
       (str "<h1>Hello user   " id "</h1>"))
