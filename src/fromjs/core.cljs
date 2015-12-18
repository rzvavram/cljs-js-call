(ns fromjs.core
  (:require [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(println "Edits to th is text should show up in your developer console.")

;; define your app data so that it doesn't get over-written on reload

;;MODEL
(defonce app-state (atom {:text "Hello " :person {:name "Dan" :age 21}}))


;;CONTROLLER
(defn ^:extern change_title [title]
  (swap! app-state assoc :text title))


(defn ^:extern change_person [person]
  (swap! app-state assoc :person (js->clj person :keywordize-keys true)))


;;VIEWS
(defn hello-world []
  [:div
    (str (@app-state :text) (get-in @app-state [:person :name]) "!")])






(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))


(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
