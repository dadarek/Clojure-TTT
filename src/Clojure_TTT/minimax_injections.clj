(ns clojure_ttt.minimax_injections
  (:use clojure.set)
  (:use clojure_ttt.rules))


;TODO: Move this into utils
(defn player-squares [board player]
  ( let [owns? #(= player (nth board %))
         not-nil? #(not= nil %)
         index-squares #(if (owns? %) (inc %))]
    (set
      (filter not-nil?
        (map index-squares (range 0 9))
      )
    )
  )
)

;TODO: Move this into utils
(defn replace-in-list [l index new-value]
  (concat
    (take index l)
    (list new-value)
    (nthnext l (inc index)))
)

;TODO: Move this into utils
(defn take-square [square context]
  (replace-in-list (:board context) (dec square) (:player context))
)

;TODO: Move this into utils
(defn empty-squares [board]
  ( let [empty-square? #(nil? (nth board %))
         not-nil? #(not= nil %)
         index-squares #(if (empty-square? %) (inc %))]
    (set
      (filter not-nil?
        (map index-squares (range 0 9))
      )
    )
  )
)

(defn wins? [square context]
  (winner? (conj (player-squares
                   (:board context)
                   (:player context))
           square))
)

(defn ties? [square context]
  (let [squares-left (#(count (filter nil? (:board context))))]
    (= 1 squares-left))
)

(defn sum-of-children [square context]
  (let [next-player (#(if (= :x (:player context)) :o :x))
        new-board (#(take-square square context))
        new-context {:player next-player
                     :board new-board} ]
  -1
  )
)
