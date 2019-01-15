python extractor.py recap.md
pandoc -V geometry='margin=1in' --toc -o notes.pdf recap.md.notes
./compile_html.sh
pandoc -t BEAMER --toc -o slides.pdf recap.md.slides
