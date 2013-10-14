(ns hello_comp.views.displays
  (:use [compojure.core]
        [hiccup core page element]
        [hiccup.form :only (form-to label text-field submit-button check-box radio-button)])
  (:require [hello_comp.views.layout :as layoutT]))

(defn skill-label [skill level value checked?]
  (label {:class "radio inline"} (str skill "-" level)
         (conj (radio-button {:id (str skill "-" level)} skill checked?  value) level)))

;;this function now works but dont like the way the parameters are passed in
(defn render-row [skill]
  [:div {:class "control-group"}
     (label {:class "control-label"} skill skill)
     [:div  {:class "controls"}
      (map skill-label [skill    skill     skill         skill]
                       ["noobie" "alright" "pretty good" "call me Gandalf"]
                       [1        2         3             4]
                       [true     false     false         false])]])


(defn skill-radio []
  (form-to {:class "form-horizontal"}[:get "/skillform"]
   [:fieldset
    [:label "Skills Form"]
    (map render-row ["Clojure" "Scala" "Java" "JBoss"])
    [:div {:class "control-group"}
     [:div {:class "controls"}
      (submit-button {:id "singlebutton" :class "btn btn-primary"} "SKILLS")]]]))



(defn indexP [counter]
  (html5 [:div (str "hello " counter)]))

(defn layout [title counter link]
  [:div
   [:h2 (str title " Counter")]
   [:div (str "The current value of counter is " counter)]
   [:div (link-to "/" "Home")]
   [:div link]])

(defn functional-handler
  "Functional style of working with a session."
  [request]
  (let [counter (if-let [counter (-> request :session :counter)]
                  (+ counter 1)
                  1)]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (html
            (layout "Functional" counter (link-to "/stateful" "Stateful")))
     :session {:counter counter}}))

(defn show-form-simple []
  (layoutT/common (skill-radio)))



(defn indexpage [counter]
  (layoutT/common (indexP counter)))
