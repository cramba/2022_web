<script setup lang="ts">
import { ref } from "vue"
const props = withDefaults(
    defineProps<{lat: number, lon: number, zoom?: number}>(),
    {zoom: 17})

    let link = `https://www.openstreetmap.org/#map=${props.zoom}/${props.lat}/${props.lon}`
</script>

<template>
    <a :href="link" target="blank"><slot>Link</slot></a>
</template>