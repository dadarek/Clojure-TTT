(ns clojure_ttt.board_utilities
  (:use clojure.set))


(defn player-squares [board player]
  ( let [owns? #(= player (nth board %))
         not-nil? #(not= nil %)
         index-squares #(if (owns? %) (inc %))]
    (set
      (filter not-nil?
        (map index-squares (range 0 9))))))

(defn replace-in-list [l index new-value]
  (concat
    (take index l)
    (list new-value)
    (nthnext l (inc index))))

(defn take-square [square context]
  (replace-in-list (:board context) (dec square) (:player context)))

(defn get-empty-squares [board]
  ( let [empty-square? #(nil? (nth board %))
         not-nil? #(not= nil %)
         index-squares #(if (empty-square? %) (inc %))]
    (set
      (filter not-nil?
        (map index-squares (range 0 9))))))

(defn count-empty-squares [board]
  (count (get-empty-squares board)))

(defn count-taken-squares [board]
  (- 9 (count-empty-squares board)))

(defn full? [board]
  (= 9 (count-taken-squares board)))

