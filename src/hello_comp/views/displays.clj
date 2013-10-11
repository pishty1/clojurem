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

(defn skills-form-simple []
  [:div {:id "shout-form" :class "sixteen columns alpha omega"}
    (form-to {:class "form-horizontal"}[:post "/"]
      [:div {:class "control-group"}
       (label {:class "control-label"} "skill" "enter a skill")
       [:div {:class "controls"}
        (text-field {:class "input-xlarge"} "skill")]]

      [:div {:class "control-group"}
      (label {:class "control-label"} "skill" "enter a skill")

        [:div {:class "controls"}
        (submit-button {:class "btn btn-primary"} "SKILLS!")]])])

(defn multi-skill-form []
  ([:div {:class "control-group"}
    (label {:class "control-label"} "skill-cb" "Java")
    [:div {:class "controls"}
     (label {:class "checkbox inline"} "checkboxes-0" "check")
     [:div {:class "controls"}
        (text-field {:class "input-xlarge"} "<input type='checkbox' name='checkboxes' id='checkboxes-0' value='1'")]]]))

(defn render-row [skill]
  [:div {:class "control-group"}
     (label {:class "control-label"} skill skill)
     [:div {:class "controls"}
      (label {:class "radio inline"} "radios-0"
         (conj (radio-button {:id "radios-0"} skill true  "1") "noobie"))
      (label {:class "radio inline"} "radios-1"
         (conj (radio-button {:id "radios-1"} skill false "2") "alright"))
      (label {:class "radio inline"} "radios-2"
         (conj (radio-button {:id "radios-2"} skill false "3") "pretty good"))
      (label {:class "radio inline"} "radios-3"
         (conj (radio-button {:id "radios-3"} skill false "4") "call me Gandalf"))]])


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



