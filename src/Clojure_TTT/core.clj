(ns clojure_ttt.core
  (:use clojure_ttt.runner)
  (:use clojure_ttt.computer)
  (:import [clojure_ttt.computer Computer]))

(def computer (Computer. :x))

(def board '(:o  nil :o
             :o  nil :x
             :x  nil :x ))

(defn -main []
  (next-move computer board))
