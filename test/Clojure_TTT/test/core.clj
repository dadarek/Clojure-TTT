(ns Clojure-TTT.test.core
  (:use [Clojure-TTT.core])
  (:use [clojure.test]))

(deftest first-test
  (is (= 3 (my-fun)))
         )
