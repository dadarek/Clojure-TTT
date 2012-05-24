(ns clojure_ttt.minimax_injections-spec
  (:use [clojure_ttt.minimax_injections])
  (:use [speclj.core]))

(describe "Minimax Injections"
  (it "Compiles"
    (should= 5 (won?))
  )
)

(run-specs)
