import React from "react"

import { Alert, AlertDescription, AlertTitle } from "./ui/alert"

type DashboardFallbackProps = {
  title: string
  description: string
}

const DashboardFallback: React.FC<DashboardFallbackProps> = ({
  title = "Error",
  description = "Please try again later.",
}) => {
  return (
    <Alert>
      <AlertTitle>{title}</AlertTitle>
      <AlertDescription>{description}</AlertDescription>
    </Alert>
  )
}
export default DashboardFallback
