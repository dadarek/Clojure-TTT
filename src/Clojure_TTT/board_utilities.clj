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

(defn take-square [square player board]
  (replace-in-list board (dec square) player))

(defn get-empty-squares [board]
  ( let [empty-square? #(nil? (nth board %))
         not-nil? #(not= nil %)
         index-squares #(if (empty-square? %) (inc %))]
    (set
      (filter not-nil?
        (map index-squares (range 0 9))))))

(defn full? [board]
  (= 0 (count (get-empty-squares board))))

(defn is-empty? [board]
  (= 9 (count (get-empty-squares board))))

