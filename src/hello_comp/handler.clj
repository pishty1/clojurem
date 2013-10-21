(ns hello-comp.handler
  (:use compojure.core
        hello-comp.db
        hello_comp.views.displays
        ring.util.response
        ring.middleware.session
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]))
  
(defroutes app-routes 
   (GET "/" {session :session}
      (let [count   (:count session 0)
        session (assoc session :count (inc count))]
    (-> (response (indexpage count))
        (assoc :session session))))

   (GET ["/skillform"] {{jb :JBoss jv :Java} :params} (str "the jbeezy is " jb " and the javaaa is " jv))


   (GET "/login" []
       (loginpage true))

   (GET "/registration/new" []
       (createlogin))
	   
	(GET "/registerUser" [emailaddress password]
       (save-user emailaddress password))

   (route/resources "/")
   (route/not-found "Not Found"))

(def app
   (->(handler/site app-routes)
   (wrap-session {:cookie-attrs {:max-age 60 :secure true}})
   (wrap-base-url)))

	;; not in use might use it as an example
    (GET ["/user/:id", :id #"[0-9]+"] {{id :id} :params}
       (str "<h1>Hello user   " id "</h1>"))
