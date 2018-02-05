python extractor.py DistributedNotes.mk
pandoc -V geometry='margin=1in' --toc -o notes.pdf DistributedNotes.mk.notes
./compile_html.sh
pandoc -t BEAMER --toc -o slides.pdf DistributedNotes.mk.slides