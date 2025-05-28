import { Fragment } from "react"

import Header from "@/components/header"

export default function GeneralLayout({
  children,
}: Readonly<{
  children: React.ReactNode
}>) {
  return (
    <Fragment>
      <Header />
      <div className="mx-auto p-6 lg:px-8">{children}</div>
    </Fragment>
  )
}
