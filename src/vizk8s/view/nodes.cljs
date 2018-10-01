(ns vizk8s.view.nodes
  (:require
   [vizk8s.model :as model]))


;(:spec (nth (get-in @vizk8s.model/app-state [:nodes])3))
; (last (:conditions (:status (second (get-in @vizk8s.model/app-state [:nodes])))))

(defn node-panel [{:keys [nodes]}]
  (into [:ul]
        (for [{{:keys [name]} :metadata {:keys [conditions]} :status} nodes]
          (do
            (println (get-in conditions [:type "Ready"]))
           [:li name]))))



