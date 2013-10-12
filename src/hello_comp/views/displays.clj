(ns hello_comp.views.displays
  (:use [hiccup core page]
        [hiccup.form :only (form-to label text-field submit-button check-box radio-button)])
  (:require [hello_comp.views.layout :as layout]))

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

(defn indexP []
  (html5 [:div {:id "header2"} "hello"]))


(defn show-form-simple []
  (layout/common (skill-radio)))



(defn indexpage []
  (layout/common (indexP)))
