import React from "react"

const Page: React.FC = () => {
  return (
    <div className="mx-auto flex w-full items-start gap-x-8 px-4 py-10 sm:px-6 lg:px-8">
      <aside className="sticky top-8 hidden w-44 shrink-0 lg:block"></aside>
      <main className="flex-1"></main>
      <aside className="sticky top-8 hidden w-96 shrink-0 xl:block"></aside>
    </div>
  )
}
export default Page
