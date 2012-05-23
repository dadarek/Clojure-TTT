(ns Clojure-TTT.test.core
  (:use [Clojure-TTT.core])
  (:use [clojure.test]))

(deftest first-test
  (is (= (+ 7 2) (+ 2 1)) 
      "Should be something?"))
