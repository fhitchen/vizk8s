(ns vizk8s.view.replicasets
  (:require
   [vizk8s.model :as model]))

(defn find-all
  [field value data] 
  (first(filter #(= value (field %)) data)))

(defn replicasets-panel [{:keys [replicasets]}]
  (into [:div {:class "flex-container"}]
        (for [{{:keys [name labels]} :metadata
               {:keys [conditions]} :status
               {:keys [selector]} :spec } replicasets]
          (do
            (println name)
            (let [app (:app labels)]
              [(keyword (str "div#" name)) name])))))
