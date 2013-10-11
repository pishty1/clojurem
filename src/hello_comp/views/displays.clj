(ns hello_comp.views.displays
  (:use [hiccup core page]
        [hiccup.form :only (form-to label text-field submit-button check-box radio-button)])
  (:require [hello_comp.views.layout :as layout]))

(defn headerTempl [title]
 [:head
  [:title title]])


(defn index-page []
  (html5
    [:html {:ng-app ""}
     (headerTempl "PicMi")
     [:body
      [:div
       [:label "Name :"]
       [:input {:type "text" :ng-model "yourName" :placeholder "Enter a name here"}]
       [:h1 "Hello {{yourName}} !"]]]]))


(defn skill-label [skill level]
  (label {:class "radio inline"} (str skill "-" level)
         (conj (radio-button {:id (str skill "-" level)} skill false  "1") level)))

;;this function now works but dont like the way the parameters are passed in, skill is coppied everywhere
(defn render-row [skill]
  [:div {:class "control-group"}
     (label {:class "control-label"} skill skill)
     [:div {:class "controls"}
      (map skill-label [skill    skill     skill         skill]
                       ["noobie" "alright" "pretty good" "call me Gandalf"])]])


(defn skill-radio []
  (form-to {:class "form-horizontal"}[:post "/"]
   [:fieldset
    [:label "Skills Form"]
    (map render-row ["Clojure" "Scala" "Java" "JBoss"])
    [:div {:class "control-group"}
     (label {:class "control-label"} "singlebutton" "")
     [:div {:class "controls"}
      (submit-button {:id "singlebutton" :name "singlebutton" :class "btn btn-primary"} "SKILLS")]]]))


(defn show-form-simple []
  (layout/common "PICMI HEADER" (skill-radio)))
