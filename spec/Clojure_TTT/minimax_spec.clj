(ns clojure_ttt.minimax-spec
  (:use [speclj.core]))

(defn equal-5? [n]
  (= 5 n))

(describe "Understanding speclj"
  (before-all 
    )

  (after-all
    )

  (before
    )

  (after
    )

  (it "Does some stuff"
    (should (equal-5? (+ 4 1))))

  (it "Does some more stuff"
    (should-not (equal-5? (+ 4 3))))

  (it "Tests against 6"
    (should= 6 (* 3 2)))
)

(run-specs)

