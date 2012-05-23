(ns clojure_ttt.speclj_sample-spec
  (:use [clojure_ttt.speclj_sample])
  (:use [speclj.core]))

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

