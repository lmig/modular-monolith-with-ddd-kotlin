#!/usr/bin/env bash
set -euo pipefail
OUT="modular-monolith-with-ddd-kotlin-$(date +%Y%m%d%H%M%S).tar.gz"
echo "Creating archive $OUT"
tar --exclude-vcs -czf "$OUT" .
echo "Archive created: $OUT"