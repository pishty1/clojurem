(ns hello_comp.views.displays
  (:use [hiccup core page]
        [hiccup.form :only (form-to label text-field submit-button)])
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

(defn skills-form []
  [:div {:id "shout-form" :class "sixteen columns alpha omega"}
    (form-to {:class "form-horizontal"}[:post "/"]
      [:div {:class "control-group"}
       (label {:class "control-label"} "skill" "enter a skill")
       [:div {:class "controls"}
        (text-field {:class "input-xlarge"} "skill")
        (submit-button "SKILLS!")]])])

(defn show-form []
  (layout/common "PICMI HEADER" (skills-form)))

